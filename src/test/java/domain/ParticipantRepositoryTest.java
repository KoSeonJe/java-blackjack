package domain;

import static org.assertj.core.api.Assertions.*;

import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ParticipantRepositoryTest {

    @DisplayName("딜러의 객체를 기본적으로 가지고 있다")
    @Test
    void test1() {
        // given
        ParticipantRepository participantRepository = ParticipantRepository.getInstance();
        Participant expectedParticipant = new Participant("딜러", null);

        // when
        Participant participant = participantRepository.getByName("딜러");

        // then
        assertThat(participant).isEqualTo(expectedParticipant);
    }

    @DisplayName("참여자 전원의 객체를 저장한다")
    @Test
    void test2() {
        //given
        List<Participant> participants = List.of(
                new Participant("mimi", Cards.createEmpty()),
                new Participant("wade", Cards.createEmpty())
        );
        ParticipantRepository participantRepository = ParticipantRepository.getInstance();

        // when
        participantRepository.addAll(participants);

        // then
        assertThat(participantRepository.getAll()).contains(
                new Participant("mimi", Cards.createEmpty()),
                new Participant("wade", Cards.createEmpty())
        );
    }
}
